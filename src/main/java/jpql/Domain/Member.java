package jpql.Domain;

import javax.persistence.*;

@Entity
@NamedQuery(
        name = "Member.findByUsername",
        query = "select m from Member m where m.username = :username"
)
@NamedQuery(
        name = "Member.count",
        query = "select count(m) from Member m"
)
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    public void changeTeam(Team team){
        team.getMembers().add(this);
        this.setTeam(team);
    }

    @Enumerated(EnumType.STRING)
    private MemberType type;

    private String username;

    private int age;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public void addteam(Team team) {
        team.getMembers().add(this);
        setTeam(team);
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", type=" + type +
                ", username='" + username + '\'' +
                ", age=" + age +
                '}';
    }

    public MemberType getType() {
        return type;
    }

    public void setType(MemberType type) {
        this.type = type;
    }
}
