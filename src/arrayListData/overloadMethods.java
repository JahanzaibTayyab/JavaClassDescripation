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
public class overloadMethods {
    public String scope;
    public String type;
    public String Name;
    public String argument;

    public overloadMethods() {
    }

    public overloadMethods(String scope, String type, String Name, String argument) {
        this.scope = scope;
        this.type = type;
        this.Name = Name;
        this.argument = argument;
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

    public String getArgument() {
        return argument;
    }

    public void setArgument(String argument) {
        this.argument = argument;
    }

    @Override
    public String toString() {
        return "OverLoad Method{" + "Scope:" + scope + ", Type:" + type + ", Name=" + Name + ", Arguments:" + argument + '}';
    }
    
    
}
