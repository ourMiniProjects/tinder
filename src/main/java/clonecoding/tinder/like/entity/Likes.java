package clonecoding.tinder.like.entity;

import jdk.jfr.Timestamp;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Time;

@Entity
@NoArgsConstructor
public class Likes extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long likedMember;

    private Long likingMember;

    public Likes(Long likedMember, Long likingMember) {
        this.likedMember = likedMember;
        this.likingMember = likingMember;
    }

    public Long getLikedMember() {
        return likedMember;
    }
}
