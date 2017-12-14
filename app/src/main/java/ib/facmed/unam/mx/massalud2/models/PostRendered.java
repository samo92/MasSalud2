package ib.facmed.unam.mx.massalud2.models;

/**
 * Created by samo92 on 29/11/2017.
 */

public class PostRendered {

    private String titulo;
    private String foto;
    private String link;
    private String categoria;

    public PostRendered(String titulo, String foto, String link, String categoria) {
        this.titulo = titulo;
        this.foto = foto;
        this.link = link;
        this.categoria = categoria;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getFoto() {
        return foto;
    }

    public String getArticulo() {
        return link;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public void setArticulo(String articulo) {
        this.link = articulo;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
