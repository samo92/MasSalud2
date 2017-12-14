package ib.facmed.unam.mx.massalud2.models;

/**
 * Created by samo92 on 28/11/2017.
 */

public class Content {

    private String rendered;
    private boolean _protected;

    /**
     * No args constructor for use in serialization
     *
     */
    public Content() {
    }

    /**
     *
     * @param rendered
     * @param _protected
     */
    public Content(String rendered, boolean _protected) {
        super();
        this.rendered = rendered;
        this._protected = _protected;
    }

    public String getRendered() {
        return rendered;
    }

    public void setRendered(String rendered) {
        this.rendered = rendered;
    }

    public boolean isProtected() {
        return _protected;
    }

    public void setProtected(boolean _protected) {
        this._protected = _protected;
    }

}
