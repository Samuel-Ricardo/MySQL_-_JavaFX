/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Samuel
 */
public class Pessoa {

    private Integer id;
    private String login;
    private String senha;
    private String email;
    private String foto;

    public Pessoa(int id, String login, String senha, String email, String foto) {
        this.id = id;
        this.login = login;
        this.senha = senha;
        this.email = email;
        this.foto = foto;
    }

    public Pessoa(String login, String senha, String email) {
        this.login = login;
        this.senha = senha;
        this.email = email;
    }

    public Pessoa() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

}
