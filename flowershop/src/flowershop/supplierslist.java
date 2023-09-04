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
public class supplierslist {
    private String sidlist,snamelist,saddlist,sphnlist;

    public supplierslist(String sidlist, String snamelist, String saddlist, String sphnlist) {
        this.sidlist = sidlist;
        this.snamelist = snamelist;
        this.saddlist = saddlist;
        this.sphnlist = sphnlist;
    }

    public String getSidlist() {
        return sidlist;
    }

    public void setSidlist(String sidlist) {
        this.sidlist = sidlist;
    }

    public String getSnamelist() {
        return snamelist;
    }

    public void setSnamelist(String snamelist) {
        this.snamelist = snamelist;
    }

    public String getSaddlist() {
        return saddlist;
    }

    public void setSaddlist(String saddlist) {
        this.saddlist = saddlist;
    }

    public String getSphnlist() {
        return sphnlist;
    }

    public void setSphnlist(String sphnlist) {
        this.sphnlist = sphnlist;
    }
    
}
