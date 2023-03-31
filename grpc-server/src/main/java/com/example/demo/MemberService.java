package com.example.demo;

import com.example.demo.model.Member;
import com.example.demo.repository.MemberRepository;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.examples.lib.Account;
import net.devh.boot.grpc.examples.lib.MembershipServiceGrpc;
import net.devh.boot.grpc.examples.lib.Post;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.NoSuchElementException;

@Slf4j
@GrpcService
@RequiredArgsConstructor
public class MemberService extends MembershipServiceGrpc.MembershipServiceImplBase{

    private final MemberRepository memberRepository;

    @Override
    public void getAccount(Post post, StreamObserver<Account> responseObserver) {

        Member member = memberRepository.findById(post.getId())
                .orElseThrow(() -> new NoSuchElementException("존재하지 않는 회원입니다."));

        Account result = Account.newBuilder()
                .setUsername(member.getUsername())
                .setProfileImageUrl(member.getProfileUrl())
                .build();

        responseObserver.onNext(result);
        responseObserver.onCompleted();
    }

}
