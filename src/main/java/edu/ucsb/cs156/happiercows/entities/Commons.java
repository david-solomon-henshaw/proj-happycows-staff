package edu.ucsb.cs156.happiercows.entities;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.AccessLevel;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Entity(name = "commons")
public class Commons
{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private String name;
  private double cowPrice;
  private double milkPrice;
  private double startingBalance;
  private Date startingDate;

  @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
  @JoinTable(name = "user_commons",
    joinColumns = @JoinColumn(name = "commons_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
  @JsonIgnore // https://www.baeldung.com/jackson-bidirectional-relationships-and-infinite-recursion
  private List<User> users;

  @Override
  public int hashCode() {
    Long boxed = id;
    return boxed.hashCode();
  }

  @Override
  public boolean equals​(Object other) {
    if (other instanceof Commons) {
      return this.hashCode() == other.hashCode();
    }

    return false;
  }
}
