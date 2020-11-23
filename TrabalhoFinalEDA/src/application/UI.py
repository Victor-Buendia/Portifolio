from chessGame.chess.ChessPosition import ChessPosition
from cpu.Suggestion import Suggestion
from estruturasDeDados import ListaDuplamenteEncadeada as Lista

class UI:

    # Printa o estado incial da partida (por rodada)
    @staticmethod
    def print_match(chess_match, stockfish):
        UI.print_board(chess_match.pieces())
        print()
        print(f'Turn: {chess_match.turn}')
        if not chess_match.checkmate:
            print(f'Esperando Jogador: {chess_match.current_player}')
            if chess_match.check:
                print('CHECK!')
            elif chess_match.draw:
                print('EMPATE!')
                print('Não ocorreu jogador vencedor')
        else:
            print('CHECKMATE!')
            print(f'Vencedor: {chess_match.current_player}')
        print(f'Evaluation: {Suggestion.get_eval(stockfish, chess_match)}')
        # chess_match.match_moves.mostrar_tras()

    # Printa o Tabuleiro
    @staticmethod
    def print_board(pieces):
        for i in range(len(pieces)):
            print(f'{(8 - i)} ', end='')
            for j in range(len(pieces)):
                UI.print_piece(pieces[i][j], False)
            print()
        print('  a b c d e f g h')

    # Printa o Tabuleiro com os movimentos da peça selecionada
    @staticmethod
    def print_board_with_moviments(pieces, possible_moves):
        for i in range(len(pieces)):
            print(f'{(8 - i)} ', end='')
            for j in range(len(pieces)):
                UI.print_piece(pieces[i][j], possible_moves[i][j])
            print()
        print('  a b c d e f g h')

    # Printa a peça
    @staticmethod
    def print_piece(piece, possible_move):
        if piece == None:
            if possible_move:
                print('.', end='')
            else:
                print('-', end='')
        else:
            print(piece, end='')
        print(' ', end='')

    @staticmethod
    def read_chess_position(message):
        position = str(input(message))
        return ChessPosition(position[0], int(position[1:]))