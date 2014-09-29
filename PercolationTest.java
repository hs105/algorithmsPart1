        //initializeGrids();
public class PercolationTest {
    private Percolation p;

    public void test1() throws Exception {
        p = new Percolation(1);
        p.open(1, 1);
        assert p.percolates();
    }

    public void test2() throws Exception {
        p = new Percolation(2);
        p.open(1, 1);
        assert p.isFull(1, 1);
        p.open(2, 1);
        assert p.percolates();
        assert p.isFull(2, 1);
    }

    public void test3() throws Exception {
        p = new Percolation(2);
        p.open(1, 1);
        p.open(2, 2);
        assert !p.percolates();
        assert !p.isFull(2, 2);
    }

    public static void main(String[] args) throws Exception {
            PercolationTest t = new PercolationTest();

            t.test1();
            t.test2();
            t.test3();
    

        /*p.open(1,1);
        System.out.println(p);
        p.open(2,2);
        System.out.println(p);
        assert !p.percolates();
        p.open(1,2);
        assert(p.percolates());
        */
        /*
        p.open(1,2);
        p.open(2,2);
        System.out.println(p);
        assert p.percolates();
        assert p.numberOfOpenSites() =  = 2;
        */

        
/*
        WeightedQuickUnionUF qu = p.getUF();
        assert qu.connected(0,1);
        assert qu.connected(0,2);
        assert qu.connected(5,3);
        assert qu.connected(5,4);
*/        
        //qu.connected(6,5);

    
    }
}
