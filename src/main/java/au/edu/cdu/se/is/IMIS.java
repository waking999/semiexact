package au.edu.cdu.se.is;

import au.edu.cdu.se.ds.AlgorithmParameter;
import au.edu.cdu.se.util.is.ISGlobalVariable;

public interface IMIS {
    void setGv(ISGlobalVariable gv);

    void setAp(AlgorithmParameter ap);

    int run();
    //int branch(ISGlobalVariable gv, AlgorithmParameter ap);
}
