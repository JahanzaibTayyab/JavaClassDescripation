/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arrayListData;

/**
 *
 * @author Admin
 */
public class UserDefineMethod {
    public String scope;
    public String type;
    public String Name;

    public UserDefineMethod() {
    }

    public UserDefineMethod(String scope, String type, String Name) {
        this.scope = scope;
        this.type = type;
        this.Name = Name;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    @Override
    public String toString() {
        return "User Define Method{" + "Scope:" + scope + ", Type=" + type + ", Name=" + Name + '}';
    }

   
    
}
