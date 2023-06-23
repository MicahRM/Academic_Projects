package Pareto;


/** This interface defines an ADT providing a Pareto Set. A pareto set is a set of
  * obsrvations where no observation in the set dominates or is dominated by any
  * other observation in the set. An observation A dominates and observation B iff
  *   1. each reading in A is <= the corresponding reading in B
  *   2. A has at least one reading that is < the corresponding reading in B
  * 
  * @see Observation
  * 
  * @version 2 (Feb 2018)
  * @author D. Hughes (after E. Foxwell)                                         */

public interface ParetoSet {
    
    
    /* This method clears the pareto set to contain no observations.             */
    
    public void clear ( ) ;
    
    
    /** This method returns the number of observations in the set.
      * 
      * @return  int  number of observations inthe set.                          */
    
    public int getSize ( ) ;
    
    
    /** This method attempts to add the observation to the set. An observation in
      * the set will be removes=d if this observation dominates it. The observation
      * is not added to the set if it is dominated by any observation already in
      * the set.
      * 
      * @param  obs  the observation to try to add
      * 
      * @throws  DimensionalityException  if obs has a different number of
      *                                   observations than the other observations
      *                                   in the set.                            */
    
    public void add ( Observation obs ) ;
    
    
    /** This method returns the observation with the best (lowest) value for the
      * ith reading.
      * 
      * @param  i  the index number for the selected reading
      * 
      * @return  Observation  the observation with the best ith reading
      * 
      * @throws  UnderflowException  if the set is empty
      * 
      * @throws  DimensionalityException  if the observations do not have an ith
      * reading                                                                  */
    
    public Observation getBestForIndex ( int i ) ;
    
    
    /** This method returns an array containing all of the observations in the set.
      * 
      * @return  Observation[]  the observations in the set as an array          */
    
    public Observation[] getAll ( ) ;
    
    
}