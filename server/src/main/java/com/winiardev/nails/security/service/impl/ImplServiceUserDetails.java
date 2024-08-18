package com.winiardev.nails.security.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.winiardev.nails.account.embeddable.Email;
import com.winiardev.nails.account.repo.RepoAccount;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ImplServiceUserDetails implements UserDetailsService {

    private final RepoAccount accountRepo;;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return accountRepo.findByEmail(new Email(email))
                .orElseThrow(() -> new UsernameNotFoundException("User not found by email: " + email));
    }

}
