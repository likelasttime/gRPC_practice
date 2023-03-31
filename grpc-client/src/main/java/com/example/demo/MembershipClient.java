package com.example.demo;

import io.grpc.StatusRuntimeException;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import net.devh.boot.grpc.examples.lib.Account;
import net.devh.boot.grpc.examples.lib.MembershipServiceGrpc;

import net.devh.boot.grpc.examples.lib.Post;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class MembershipClient {

    @GrpcClient("grpc-server")
    MembershipServiceGrpc.MembershipServiceBlockingStub membershipStub;

    public Map<String, String> findProfile(Long id) {
        Map<String, String> result = new HashMap<String, String>();
        try {
            Account account = membershipStub.getAccount(Post.newBuilder().setId(id).build());
            result.put("username", account.getUsername());
            result.put("profileImageUrl", account.getProfileImageUrl());
        } catch(final StatusRuntimeException e) {
            log.error("Request failed", e);
            result.put("error", e.getStatus().getCode().toString());
        }
        return result;
    }
}
