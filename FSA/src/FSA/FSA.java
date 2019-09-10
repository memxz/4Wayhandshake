package FSA;

import java.util.*;
import Scanner.*;

/**
 * FSA - a finite state automaton.
 * To use it, hand the constructor that is connected to a grammar file.
 * The file indicates the legal sentences in the command language.
 * Each sentence can consist of string literals or the special 
 * tokens <int> and <string>, which mean match any integer or match
 * any string, respectively.
 * The end of a sentence is indicated by a single + or : -- + means
 * increment the action number and then associate it with this sentence;
 * : means associate the current action number with this sentence.
 * (See grammar.txt.)  This change to the original FSA spec was made
 * because I was tired of having to manually update the action numbers
 * in the grammar file as I added more commands.
 */
public class FSA {

    private FSAState    startState;
    private ArrayList   parsedLine;  // will hold the tokens we've seen
                                     // if an input sentence is accepted

    public FSA( Scanner scanner ) throws FSAException {
        FSAState   currentState = null;
        FSAState   nextState = null;
        int        action = -1;   

        startState = new FSAState();
        currentState = startState;

        try {
            while ( scanner.hasNextToken() ) {

                String nextToken = scanner.getNextToken();

                if ( !scanner.hasNextToken() ) {
                    break;  // all done

                } else if ( nextToken.equals(":") ) {
                    currentState.markAccepting( action );
                    currentState = startState;
                
                } else if ( nextToken.equals("+") ) {
                    action = action + 1;
                    currentState.markAccepting( action );
                    currentState = startState;
                
                } else {
                    nextState = currentState.makeTransition( nextToken );
                    if ( nextState == null ) {
                        nextState = new FSAState();
                        currentState.addTransition( nextToken, nextState );
                    }
                    currentState = nextState;
                }

            } // end of while loop
        } catch (Exception e) {
            throw new FSAException ("Syntax error in grammar file: " + e.getMessage() );
        }
    }

    /**
     * readCommand() - read input from scanner.  Construct an ArrayList
     * of the input tokens, to provide the caller if the input sentence is accepted.
     * Throw an exception if the input is not acceptable.
     */
    public int readCommand( Scanner scanner ) throws FSAException {
        FSAState   currentState;
        String     token;

        parsedLine = new ArrayList();
        currentState = startState;

        while ( !currentState.IsAcceptingState() ) {
            try {
                token = scanner.getNextToken();
            } catch (Exception e) {
                throw new FSAException( "FSA caught scanner exception: " + e.getMessage() );
            }
            currentState = currentState.makeTransition( token );
            if ( currentState == null ) {
                throw new FSAException( token );
            }
            parsedLine.add( token );
        }
        return currentState.getAcceptResult();
    }

    /**
     * Routine to retrieve the parsed input line.
     */
    public ArrayList getParsedCommand() {
        return parsedLine;
    }

    /**
     * toString() - the usual utility routine
     */
    public String toString() {
        String result = startState.toString( "" );
        return result;
    }

    /**
     * This main is just for debugging the FSA and/or grammar files.
     * To use it you must pass the filename as the
     * only parameter on the command line, e.g.,
     *    java FSA grammar.txt
     */
    public static void main( String args[] ) {
        FSA      fsa;
        Scanner  scanner;

        System.out.println("Constructing FSA from grammar in " + args[0] );

        try {
            scanner = new Scanner( args[0] );
            scanner.turnEOLNOff();
            fsa = new FSA( scanner );
        } catch (Exception e) {
            System.out.println("Error constructing scanner: " + e.getMessage() );
            return;
        }

        System.out.println( "Constructed FSA = " );
        System.out.println( fsa.toString() );

        try {
            scanner = new Scanner( null );
        } catch (Exception e) {
            System.out.println("Error creating keyboard scanner: " + e.getMessage() );
            return;
        }
        System.out.println("\nType command lines...");
        while ( scanner.hasNextToken() ) {
            try {
                int result = fsa.readCommand( scanner );
                System.out.println("Got " + result);
            } catch (Exception e) {
                System.out.println( "Error: " + e.getMessage() );
                scanner.flushLine();
            }
        }
    }
}