package application.project.mhm.data_transfer_objects;

import java.io.Serializable;

/**
 * Created by Seungho Han on 2017-05-03.
 */

public class ProfileAccount implements Serializable {
    private String name = null;
    private String Id = null;
    private String password = null;

    public ProfileAccount(String password, String id) {
        this.password = password;
        this.Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "ProfileAccount{" +
                "name='" + name + '\'' +
                ", Id='" + Id + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
