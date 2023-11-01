class sim{

    int direction[2] = {0, 1);

    int x, y;


    void move {
        this.x += direction[0];
        this.y += direction[1];
    }

    void turn {
        // {0, 1}, {1, 0}, {0, -1}, {-1, 0}
        switch (direction) {
            case {0, 1}:
                direction = {1, 0};
                break;
            case {1, 0}:
                direction = {0, -1};
                break;
            case {0, -1}:
                direction = {-1, 0};
                break;
            case {-1, 0}:
                direction = {0, 1};
                break;
        }

    }

    public sim(int x, int y){
        this.x = x;
        this.y = y;
    }
}
