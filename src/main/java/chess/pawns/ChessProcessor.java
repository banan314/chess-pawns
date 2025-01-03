package chess.pawns;

import javax.validation.constraints.NotNull;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;

public class ChessProcessor {

    public void process() throws Exception {
        final String DATABASE_FILENAME = "src/main/resources/lichess_db_standard_rated_2015-10.pgn";
        final String OUT_FILENAME = "src/main/resources/games_with_consecutive_pawn_captures_c_to_f.txt";
        try (var reader = new BufferedReader(new FileReader(DATABASE_FILENAME))) {
            try(var writer = new PrintWriter(OUT_FILENAME)) {
                process(reader, writer);
            }
        }
    }

    void process(BufferedReader reader, PrintWriter writer) throws Exception {
        String line;
        while ((line = reader.readLine()) != null) {
            if (!line.trim().startsWith("1.")) {
                continue;
            }
            var moves = line.split("\\d+[.-]"); // include 2. and 1-0
            ArrayList<String> whiteMoves = new ArrayList<>(), blackMoves = new ArrayList<>();

            for (int j = 1; j < moves.length; j++) {
                String move = moves[j];
                var halfMoves = move.trim().split(" ");
                whiteMoves.add(halfMoves[0]);
                if (halfMoves.length > 1)
                    blackMoves.add(halfMoves[1]);
            }

            if (hasConsecutivePawnCaptures(whiteMoves.toArray(new String[0]))
                    || hasConsecutivePawnCaptures(blackMoves.toArray(new String[0]))) {
                writer.println(line);
                writer.println();
                writer.println();
            }
        }
    }

    /**
     * Check if there are consecutive pawn captures in the given array of moves of a single color.
     *
     * @param  moves   array of the moves of only one color
     * @return         true if there are consecutive pawn captures from c to files, false otherwise
     */
    boolean hasConsecutivePawnCaptures(@NotNull String[] moves) {
        char[] fromFiles = {'c', 'd', 'e', 'f'};
        int currentFrom = 0;
        String currentSquare = null;
        for (int i = 0; i < moves.length - 1; i++) {
            String move = moves[i];
            if(currentSquare != null && isMoveForward(currentSquare, move)) {
                currentSquare = move;
                continue;
            }
            if (isPawnCaptureAndOnTheRightFiles(fromFiles[currentFrom], fromFiles[currentFrom + 1], move)) {
                // check if the next move is to the square forward from the current square
                if (currentSquare != null && !isCaptureForwardDiagonally(currentSquare, move)) {
                    continue;
                }
                currentSquare = move.substring(2, 4);
                if (currentFrom == 2) {
                    return true;
                }
                currentFrom++;
            }
        }
        return false;
    }

    boolean isMoveForward(String currentSquare, String move) {
        return currentSquare.charAt(0) == move.charAt(0) &&  currentSquare.charAt(1) + 1 == move.charAt(1);
    }

    // we assume it's a pawn capture and it was already checked
    boolean isCaptureForwardDiagonally(String currentSquare, String move) {
        return currentSquare.charAt(1) + 1 == move.charAt(3);
    }

    boolean isPawnCaptureAndOnTheRightFiles(char fromFile, char toFile, String move) {
        return move.length() >= 3 && move.charAt(0) == fromFile && move.charAt(1) == 'x' && move.charAt(2) == toFile;
    }
}
