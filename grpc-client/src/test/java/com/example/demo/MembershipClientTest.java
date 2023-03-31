package com.example.demo;

import net.devh.boot.grpc.examples.lib.Account;
import net.devh.boot.grpc.examples.lib.MembershipServiceGrpc;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MembershipClientTest {

    @InjectMocks
    private MembershipClient membershipClient;

    @Mock
    private MembershipServiceGrpc.MembershipServiceBlockingStub membershipStub;

    @Test
    public void testFindProfile(){

        final String username = "user";
        final String profileImageUrl = "user.jpg";
        Map<String, String> result = new HashMap<>();
        result.put("username", username);
        result.put("profileImageUrl", profileImageUrl);
        when(membershipStub.getAccount(any()))
                .thenReturn(Account.newBuilder().setUsername(username).setProfileImageUrl(profileImageUrl).build());

        Map<String, String> response = membershipClient.findProfile(anyLong());

        assertThat(response.get("username")).isEqualTo(username);
        assertThat(response.get("profileImageUrl")).isEqualTo(profileImageUrl);
    }
}
