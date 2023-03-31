package com.example.demo;

import com.example.demo.model.Member;
import com.example.demo.repository.MemberRepository;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.examples.lib.Account;
import net.devh.boot.grpc.examples.lib.Post;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@SpringBootTest
@SpringJUnitConfig(classes = {MemberServiceUnitTestConfiguration.class})
public class MemberServiceTest {

    @InjectMocks
    private MemberService memberService;

    @Mock
    private MemberRepository memberRepository;

    @Test
    public void testGetAccount() throws Exception {

        final String username = "user";
        final String profileImageUrl = "user.jpg";
        Account account = Account.newBuilder()
                .setUsername(username)
                .setProfileImageUrl(profileImageUrl)
                .build();
        StreamObserver streamObserver = mock(StreamObserver.class);
        when(memberRepository.findById(anyLong())).thenReturn(Optional.of(Member
                .builder()
                        .username(username)
                        .profileUrl(profileImageUrl)
                .build()));

        memberService.getAccount(Post.newBuilder().setId(anyLong()).build(), streamObserver);

        verify(streamObserver).onNext(account);
        verify(streamObserver).onCompleted();
    }

    @Test
    public void FailTestGetAccount() throws Exception {

        StreamObserver streamObserver = mock(StreamObserver.class);
        when(memberRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class,
                () -> memberService.getAccount(Post.newBuilder().setId(1L).build(), streamObserver));
    }
}
