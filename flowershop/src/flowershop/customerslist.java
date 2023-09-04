/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flowershop;

/**
 *
 * @author User
 */
public class customerslist {
    private String funcid,funcfname,funclname,funcaddress,funcphone;

    public customerslist(String funcid, String funcfname, String funclname, String funcaddress, String funcphone) {
        this.funcid = funcid;
        this.funcfname = funcfname;
        this.funclname = funclname;
        this.funcaddress = funcaddress;
        this.funcphone = funcphone;
    }

    public String getFuncid() {
        return funcid;
    }

    public void setFuncid(String funcid) {
        this.funcid = funcid;
    }

    public String getFuncfname() {
        return funcfname;
    }

    public void setFuncfname(String funcfname) {
        this.funcfname = funcfname;
    }

    public String getFunclname() {
        return funclname;
    }

    public void setFunclname(String funclname) {
        this.funclname = funclname;
    }

    public String getFuncaddress() {
        return funcaddress;
    }

    public void setFuncaddress(String funcaddress) {
        this.funcaddress = funcaddress;
    }

    public String getFuncphone() {
        return funcphone;
    }

    public void setFuncphone(String funcphone) {
        this.funcphone = funcphone;
    }
    
    
}
