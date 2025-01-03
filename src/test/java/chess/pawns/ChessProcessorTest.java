package chess.pawns;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ChessProcessorTest {

    @Test
    void testIsMoveForward() throws Exception {
        var processor = new ChessProcessor();
        assertTrue(processor.isMoveForward("a2", "a3"));
        assertFalse(processor.isMoveForward("a2", "b3"));
        assertFalse(processor.isMoveForward("a2", "a4"));
        assertFalse(processor.isMoveForward("a2", "a1"));
        assertTrue(processor.isMoveForward("c5", "c6"));
        assertFalse(processor.isMoveForward("c5", "d5"));
    }

    @Test
    void testhasConsecutivePawnCaptures() throws Exception {
        var processor = new ChessProcessor();
        assertFalse(processor.hasConsecutivePawnCaptures(new String[]{"a2", "b3", "c4", "d5", "e6", "f7", "g8", "h9"}));
        assertFalse(processor.hasConsecutivePawnCaptures(new String[]{"c3", "b3", "cxd4", "d5", "dxe5", "f7", "exf6", "h9"}));
        assertTrue(processor.hasConsecutivePawnCaptures(new String[]{"cxd4", "d5", "dxe6", "exf7", "fxg8B", "h9"}));
    }

    @Test
    void testIsPawnCapture() throws Exception {
        var processor = new ChessProcessor();
        assertTrue(processor.isPawnCaptureAndOnTheRightFiles('c', 'd', "cxd1"));
        assertTrue(processor.isPawnCaptureAndOnTheRightFiles('c', 'd', "cxd2"));
        assertTrue(processor.isPawnCaptureAndOnTheRightFiles('c', 'd', "cxd3"));
        assertTrue(processor.isPawnCaptureAndOnTheRightFiles('c', 'd', "cxd4"));
        assertTrue(processor.isPawnCaptureAndOnTheRightFiles('c', 'd', "cxd5"));
        assertTrue(processor.isPawnCaptureAndOnTheRightFiles('c', 'd', "cxd6"));
        assertTrue(processor.isPawnCaptureAndOnTheRightFiles('c', 'd', "cxd7"));
        assertTrue(processor.isPawnCaptureAndOnTheRightFiles('c', 'd', "cxd8"));

        assertFalse(processor.isPawnCaptureAndOnTheRightFiles('c', 'd', "dxe5"));
    }

    @Test
    void testIsCaptureForwardDiagonally() throws Exception {
        var processor = new ChessProcessor();
        assertTrue(processor.isCaptureForwardDiagonally("a2", "bxc3"));
        assertFalse(processor.isCaptureForwardDiagonally("a2", "bxa1"));
        assertFalse(processor.isCaptureForwardDiagonally("a2", "axa1"));
        assertTrue(processor.isCaptureForwardDiagonally("c5", "dxc6"));
        assertFalse(processor.isCaptureForwardDiagonally("c5", "cxd4"));
        assertFalse(processor.isCaptureForwardDiagonally("c5", "bxc5"));
        assertFalse(processor.isCaptureForwardDiagonally("c5", "dxe5"));
    }
}