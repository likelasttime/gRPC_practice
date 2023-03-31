package com.example.demo;

import com.example.demo.repository.MemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.mockito.Mockito.mock;

@Configuration
public class MemberServiceUnitTestConfiguration {

    @Bean
    MemberRepository memberRepository(){
        return mock(MemberRepository.class);
    };
}
