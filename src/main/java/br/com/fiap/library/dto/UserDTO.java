package br.com.fiap.library.dto;

import br.com.fiap.library.entity.User;

public class UserDTO {

    private Integer id;
    private String username;

    public UserDTO(User savedUser) {
        this.id = savedUser.getId();
        this.username = savedUser.getUsername();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
