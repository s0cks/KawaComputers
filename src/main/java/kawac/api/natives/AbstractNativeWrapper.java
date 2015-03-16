package kawac.api.natives;

public abstract class AbstractNativeWrapper{
    public abstract String getName();

    @Override
    public final boolean equals(Object obj){
        if(obj instanceof AbstractNativeWrapper){
            return ((AbstractNativeWrapper) obj).getName().equalsIgnoreCase(this.getName());
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