package mate.academy.internetshop.model;

public class Role {

    private static Long idProducer = 0L;
    private Long id;
    private RoleName roleName;

    public Role() {
        setId(++idProducer);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RoleName getRoleName() {
        return roleName;
    }

    public void setRoleName(RoleName roleName) {
        this.roleName = roleName;
    }

    public enum RoleName {
        USER, ADMIN;
    }
}
