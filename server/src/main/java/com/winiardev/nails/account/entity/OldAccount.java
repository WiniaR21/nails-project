package com.winiardev.nails.account.entity;

import java.time.LocalDateTime;

import com.winiardev.nails.account.embeddable.Email;
import com.winiardev.nails.account.embeddable.FullName;
import com.winiardev.nails.account.embeddable.Password;
import com.winiardev.nails.account.embeddable.PhoneNumber;
import com.winiardev.nails.account.embeddable.Role;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents an old version of an account in the system, preserving account
 * details for auditing or rollback purposes.
 * 
 * @author winiar.dev
 */
@Entity
@Data
@NoArgsConstructor
@Table(name = "old_nails_accounts")
public class OldAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "old_account_id")
    private String oldAccountId;

    @Embedded
    @AttributeOverride(name = "email", column = @Column(name = "email", updatable = false))
    private Email email;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "firstName", column = @Column(name = "first_name", updatable = false)),
            @AttributeOverride(name = "lastName", column = @Column(name = "last_name", updatable = false))
    })
    private FullName fullName;

    @Embedded
    @AttributeOverride(name = "password", column = @Column(name = "password", updatable = false))
    private Password password;

    @Embedded
    @AttributeOverride(name = "phoneNumber", column = @Column(name = "phone_number", updatable = false))
    private PhoneNumber phoneNumber;

    @Enumerated
    private Role role;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    /**
     * Constructs an {@link OldAccount} instance by copying details from an existing
     * {@link Account}.
     *
     * @param account the {@link Account} instance to copy details from.
     */
    public OldAccount(Account account) {
        this.email = account.getEmail();
        this.password = new Password(account.getPassword());
        this.phoneNumber = account.getPhoneNumber();
        this.fullName = account.getFullName();
        this.role = account.getRole();
        this.createdAt = account.getCreatedAt();
        this.updatedAt = account.getUpdatedAt();
    }
}
