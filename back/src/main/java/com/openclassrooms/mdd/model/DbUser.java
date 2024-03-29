package com.openclassrooms.mdd.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.*;
import java.util.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * Model class representing a user entity.
 */
@Data
@Accessors(chain = true)
@Builder
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(
  name = "user",
  uniqueConstraints = {
    @UniqueConstraint(columnNames = "email"),
    @UniqueConstraint(columnNames = "username"),
  }
)
public class DbUser {

  /** The unique identifier of the user. */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  /** The email address of the user. */
  @NonNull
  @Size(max = 50)
  @Email
  @Column(name = "email")
  private String email;

  /** The username of the user. */
  @NonNull
  @Size(max = 25)
  @Column(name = "username")
  private String username;

  /** The password of the user. */
  @NonNull
  @Size(min = 8, max = 120)
  @Pattern(
    regexp = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!£°;@#$%^&*()-+=]).*",
    message = "Password must meet the specified criteria."
  )
  @Column(name = "password")
  private String password;

  /** The list of posts authored by the user. */
  @OneToMany(
    mappedBy = "dbUser",
    cascade = CascadeType.ALL,
    orphanRemoval = true,
    fetch = FetchType.LAZY
  )
  private List<Post> posts;

  /** The list of comments authored by the user. */
  @OneToMany(
    mappedBy = "dbUser",
    cascade = CascadeType.ALL,
    orphanRemoval = true,
    fetch = FetchType.LAZY
  )
  private List<Comment> comments;

  /** The list of subjects associated with the user. */
  @ManyToMany
  @JoinTable(
    name = "user_subject",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "subject_id")
  )
  private List<Subject> subjects;
}
