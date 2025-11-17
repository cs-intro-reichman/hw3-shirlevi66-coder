// Computes the periodical payment necessary to pay a given loan.
public class LoanCalc {
	
	static double epsilon = 0.001;  // Approximation accuracy
	static int iterationCounter;    // Number of iterations 
	
	// Gets the loan data and computes the periodical payment.
    // Expects to get three command-line arguments: loan amount (double),
    // interest rate (double, as a percentage), and number of payments (int).  
	public static void main(String[] args) {		
		// Gets the loan data
		double loan = Double.parseDouble(args[0]);
		double rate = Double.parseDouble(args[1]);
		int n = Integer.parseInt(args[2]);
		System.out.println("Loan = " + loan + ", interest rate = " + rate + "%, periods = " + n);

		// Computes the periodical payment using brute force search
		System.out.print("\nPeriodical payment, using brute force: ");
		System.out.println((int) bruteForceSolver(loan, rate, n, epsilon));
		System.out.println("number of iterations: " + iterationCounter);

		// Computes the periodical payment using bisection search
		System.out.print("\nPeriodical payment, using bi-section search: ");
		System.out.println((int) bisectionSolver(loan, rate, n, epsilon));
		System.out.println("number of iterations: " + iterationCounter);
	}
	static final double EPSILON = 0.0001;
	// Computes the ending balance of a loan, given the loan amount, the periodical
	// interest rate (as a percentage), the number of periods (n), and the periodical payment.
	private static double endBalance(double loan, double rate, int n, double payment) {	
		
	 double balance = loan;

    for (int i = 0; i < n; i++) {
        balance = (balance - payment) * (1 + rate);
    }
    return balance;
}
	
	// Uses sequential search to compute an approximation of the periodical payment
	// that will bring the ending balance of a loan close to 0.
	// Given: the sum of the loan, the periodical interest rate (as a percentage),
	// the number of periods (n), and epsilon, the approximation's accuracy
	// Side effect: modifies the class variable iterationCounter.
    public static double bruteForceSolver(double loan, double rate, int n, double epsilon) {
		iterationCounter = 0;

    double g = loan / n;  
    double balance = endBalance(loan, rate, n, g);

    while (balance > 0) {
        iterationCounter++;
        g = g + EPSILON;  
        balance = endBalance(loan, rate, n, g);
    }

    return g;
}
    
    // Uses bisection search to compute an approximation of the periodical payment 
	// that will bring the ending balance of a loan close to 0.
	// Given: the sum of the loan, the periodical interest rate (as a percentage),
	// the number of periods (n), and epsilon, the approximation's accuracy
	// Side effect: modifies the class variable iterationCounter.
    public static double bisectionSolver(double loan, double rate, int n, double epsilon) {  
        iterationCounter = 0;

    double lo = loan / n;
    double fLo = endBalance(loan, rate, n, lo);

    double hi = loan;
    double fHi = endBalance(loan, rate, n, hi);

    double g = 0.0;
    double fG = 0.0;

    while ((hi - lo) > EPSILON) {
        iterationCounter++;

        g = (lo + hi) / 2.0;
        fG = endBalance(loan, rate, n, g);

        if (fG == 0.0) {
            return g;
        }

        if (fG * fLo > 0) {
            lo = g;
            fLo = fG;
        } else {
            hi = g;
            fHi = fG;
        }
    }

    return (lo + hi) / 2.0;
}
}