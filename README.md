# Chess pawns

Java program to discover if there was a chess game in which the c pawn reached the f file 

Rules:
* it has to be the same pawn, if it was captured it's dead
* it cannot promote in the meantime
* en passant doesn't matter
* it can be a pawn that used to be on the b file and captured on c3 for example and then reached the f file
* it can be immediately killed on the f file
* it can move forward however many times you want

# Database

You can download the pgn games database from lichess, for example https://database.lichess.org/. I used the database of games 
from October 2015. 

# Result

In October 2015, there were **6194 games** on lichess.com in which a white c pawn reached the f file. I saw a few games and some of them were
played by quite strong players. In the games I saw it was an accelerated Grünfeld variation in which there was a pawn exchange
on d4, then a white knight jumped on e5 and was exchanged there. White pawn recaptured from d4 to e5. The game transitioned into
the endgame, in which Black played ...f6 to chip away the white e5 pawn and White took on f6, therefore the pawn reached from
c3 to f6 through the whole game.