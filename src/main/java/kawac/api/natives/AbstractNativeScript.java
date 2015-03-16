package kawac.api.natives;

public abstract class AbstractNativeScript{
    public abstract String getName();

    @Override
    public final boolean equals(Object obj){
        if(obj instanceof AbstractNativeScript){
            return ((AbstractNativeScript) obj).getName().equalsIgnoreCase(this.getName());
        } else{
            return false;
        }
    }

    @Override
    public final int hashCode(){
        return 37 * this.getName().hashCode();
    }

    @Override
    public final String toString(){
        return this.getName();
    }
}