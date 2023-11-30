package sim;

public class movement {

    public int[] forward(int[] pos, int[] direction)
    {
        return new int[]{pos[0] + direction[0], pos[1]+ direction[1]};
    }

    public int[] turn(int[] pos, int[] direction)
    {
        if (direction[0] == 0 && direction[1] == 1){
            direction[0] = 1;
            direction[1] = 0;
        }
        else if (direction[0] == 1 && direction[1] == 0){
            direction[0] = 0;
            direction[1] = -1;
        }
        else if (direction[0] == 0 && direction[1] == -1){
            direction[0] = -1;
            direction[1] = 0;
        }
        else if (direction[0] == -1 && direction[1] == 0){
            direction[0] = 0;
            direction[1] = 1;
        }
        return direction;
    }

}


