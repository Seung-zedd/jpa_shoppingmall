package com.mysite.jpa_shoppingmall.member.repository;

import com.mysite.jpa_shoppingmall.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    /**
 * Retrieves a Member entity by its email address.
 *
 * @param email the email address to search for
 * @return the Member with the specified email, or null if not found
 */
Member findByEmail(String email);
}
