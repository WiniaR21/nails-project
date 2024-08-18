package com.winiardev.nails.account.entity;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.winiardev.nails.account.embeddable.Email;
import com.winiardev.nails.account.embeddable.FullName;
import com.winiardev.nails.account.embeddable.Password;
import com.winiardev.nails.account.embeddable.PhoneNumber;
import com.winiardev.nails.account.embeddable.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents an account in the system, implementing Spring Security's
 * {@link UserDetails}.
 * 
 * @author winiar.dev
 */
@Entity
@Data
@NoArgsConstructor
@Table(name = "nails_accounts")
public class Account implements UserDetails {

    @Id
    private Email email;

    @Embedded
    private FullName fullName;

    @Embedded
    private Password password;

    @Embedded
    private PhoneNumber phoneNumber;

    @Enumerated
    private Role role;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    /**
     * Returns the authorities granted to the user. In this case, it returns a list
     * with a single authority based on the user's role.
     *
     * @return a collection of {@link GrantedAuthority} objects.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    /**
     * Returns the username of the user. This is typically used for authentication
     * purposes.
     *
     * @return the username (email) of the user.
     */
    @Override
    public String getUsername() {
        return email.email();
    }

    /**
     * Indicates whether the user's account has expired. Accounts that are expired
     * should not be allowed to authenticate.
     *
     * @return {@code true} if the account is not expired, {@code false} otherwise.
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Indicates whether the user is locked or disabled. Locked or disabled accounts
     * should not be allowed to authenticate.
     *
     * @return {@code true} if the account is not locked, {@code false} otherwise.
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Indicates whether the user's credentials (password) have expired. Expired
     * credentials should not be allowed to authenticate.
     *
     * @return {@code true} if the credentials are not expired, {@code false}
     *         otherwise.
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Indicates whether the user is enabled or disabled. Disabled accounts should
     * not be allowed to authenticate.
     *
     * @return {@code true} if the account is enabled, {@code false} otherwise.
     */
    @Override
    public boolean isEnabled() {
        return true;
    }

    /**
     * Returns the password of the user.
     *
     * @return the user's password.
     */
    @Override
    public String getPassword() {
        return password.password();
    }

    /**
     * Private constructor for the {@link AccountBuilder}.
     * 
     * @param builder The builder used to create the {@link Account} instance.
     */
    private Account(AccountBuilder builder) {
        this.email = new Email(builder.email);
        this.fullName = new FullName(builder.firstName, builder.lastName);
        this.password = new Password(builder.password);
        this.phoneNumber = new PhoneNumber(builder.phoneNumber);
        this.role = builder.role;
        this.createdAt = builder.createdAt;
        this.updatedAt = builder.updatedAt;
    }

    /**
     * Builder for the {@link Account} class, providing a fluent API for creating
     * {@link Account} instances.
     */
    public static class AccountBuilder {
        private String email;
        private String firstName;
        private String lastName;
        private String password;
        private String phoneNumber;
        private Role role;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        /**
         * Sets the email for the {@link Account} being built.
         *
         * @param email the email address.
         * @return the current instance of {@link AccountBuilder}.
         */
        public AccountBuilder email(String email) {
            this.email = email;
            return this;
        }

        /**
         * Sets the first name for the {@link Account} being built.
         *
         * @param firstName the first name.
         * @return the current instance of {@link AccountBuilder}.
         */
        public AccountBuilder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        /**
         * Sets the last name for the {@link Account} being built.
         *
         * @param lastName the last name.
         * @return the current instance of {@link AccountBuilder}.
         */
        public AccountBuilder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        /**
         * Sets the password for the {@link Account} being built.
         *
         * @param password the password.
         * @return the current instance of {@link AccountBuilder}.
         */
        public AccountBuilder password(String password) {
            this.password = password;
            return this;
        }

        /**
         * Sets the phone number for the {@link Account} being built.
         *
         * @param phoneNumber the phone number.
         * @return the current instance of {@link AccountBuilder}.
         */
        public AccountBuilder phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        /**
         * Sets the role for the {@link Account} being built.
         *
         * @param role the role.
         * @return the current instance of {@link AccountBuilder}.
         */
        public AccountBuilder role(Role role) {
            this.role = role;
            return this;
        }

        /**
         * Sets the createdAt date for the {@link Account} being built.
         *
         * @param date the date.
         * @return the current instance of {@link AccountBuilder}.
         */
        public AccountBuilder createdAt(LocalDateTime date) {
            this.createdAt = date;
            return this;
        }

        /**
         * Sets the updatedAt date for the {@link Account} being built.
         *
         * @param date the date.
         * @return the current instance of {@link AccountBuilder}.
         */
        public AccountBuilder updatedAt(LocalDateTime date) {
            this.updatedAt = date;
            return this;
        }

        /**
         * Builds a new {@link Account} instance with the provided properties.
         *
         * @return a new {@link Account} instance.
         */
        public Account build() {
            return new Account(this);
        }
    }
}
