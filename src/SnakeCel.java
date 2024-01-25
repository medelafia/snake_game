public class SnakeCel{
    public int X_SNACK ;
    public int Y_SNACK ;
    public String DIRECTION = "right" ;

    public int width = 10 ;
    public int height = 10 ;
    public SnakeCel()  {
        this.X_SNACK = 10 ;
        this.Y_SNACK = 10 ;
        this.DIRECTION = "right" ;
    }

    public SnakeCel(int X_SNACK , int Y_SNACK , String direction ) {
        this.X_SNACK = X_SNACK ;
        this.Y_SNACK = Y_SNACK ;
        this.DIRECTION = direction ;
    }
    public void moveRight(int width){
        if(X_SNACK < width ){
            X_SNACK +=10;
        }else {
            X_SNACK = 0;
        }
    }
    public void moveLeft(int width){
        if(X_SNACK > 0 ){
            X_SNACK -=10;
        }else {
            X_SNACK = width - 10 ;
        }
    }
    public void moveDown(int height){
        if(Y_SNACK < height){
            Y_SNACK +=10;
        }else {
            Y_SNACK = 0;
        }
    }
    public void moveUp(int height){
        if(Y_SNACK > 0 ){
            Y_SNACK -=10;
        }else {
            Y_SNACK = height - 10;
        }
    }

    public void setDIRECTION(String DIRECTION) {
        this.DIRECTION = DIRECTION;
    }
}
