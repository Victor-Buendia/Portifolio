#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <stdbool.h>
#include <string.h>
#include <conio.h>

const int W = 80;
const int H = 40;

int map[40][80];
int coord[15][2];
int comandos[15][7];

int player_x;
int player_y;

//numero de bots
int n = 0;

void render(){
    system("cls");
    printf("####################################################################################\n");
    for(int y = 0; y < H; y++){
        printf("# ");
        for(int x = 0; x < W; x++){
            if(map[y][x] == '?'){
                map[y][x] = '#';
            }
            if((y >= player_y-2 && y <= player_y+2) && (x >= player_x-3 && x <= player_x+3)){
                if((y == player_y-2 && x == player_x-3) || (y == player_y+2 && x == player_x-3) || (y == player_y-2 && x == player_x+3) || (y == player_y+2 && x == player_x+3)){
                    printf(" ");
                }
                else{
                    printf("%c", map[y][x]);
                }
            }
            else{
                printf(" ");
            }
        }
        printf(" #\n");
    }
    printf("####################################################################################\n");
}

void menu(){
    char ch;

    system("cls");
    printf(" ________________________\n");
    printf("|          MENU          |\n");
    printf("|                        |\n");
    printf("|       T - TECLAS       |\n");
    printf("|      ESC - VOLTAR      |\n");
    printf("|        K - SAIR        |\n");
    printf("|________________________|\n");

    ch = getch();
    if(ch == 't'){
        //teclas();
    }
    else if(ch == 'k'){
        exit(0);
    }
}

int rangedrand(int menor_valor, int maior_valor){
    return menor_valor + (rand() % (maior_valor - menor_valor));
}

void caverna(bool primeira_caverna){

    //dimensão da caverna é (w + 1) * (h + 1)
    int w = rangedrand(5, 15);
    int h = rangedrand(3, 9);
    
    //canto de cima da parede
    int x0 = rangedrand(0, ((W - 1) - w) - 1);
    int y0 = rangedrand(0, ((H - 1) - h) - 1);

    //canto de baixo da parede
    int x1 = x0 + w + 2;
    int y1 = y0 + h + 2;

    for(int y = y0; y <= y1; y++){
        for(int x = x0; x <= x1; x++){
            if(map[y][x] == '.'){
                return;
            }
        }
    }

    int numero_portas_consideras_posicionadas = 0;
    int porta_x, porta_y;

    if(!primeira_caverna){
        for(int y = y0; y <= y1; y++){
            for(int x = x0; x <= x1; x++){
                int s = (x == x0 || x == x1);
                int t = (y == y0 || y == y1);

                if(s ^ t && map[y][x] == '#'){
                    numero_portas_consideras_posicionadas++;
                    if(rangedrand(0, numero_portas_consideras_posicionadas) == 0){
                        porta_x = x;
                        porta_y = y;
                    }
                }
            }
        }
        if(numero_portas_consideras_posicionadas == 0){
            return;
        }
    }

    for(int y = y0; y <= y1; y++){
        for(int x = x0; x <= x1; x++){
            int s = (x == x0 || x == x1);
            int t = (y == y0 || y == y1);

            map[y][x] = s && t ? '?' : s ^ t ? '#' : '.';
            
        }
    }

    if(primeira_caverna){
        player_y = rangedrand(y0 + 1, y1 - 1);
        player_x = rangedrand(x0 + 1, x1 - 1);
        map[player_y][player_x] = '@';
    }
    else{
        map[porta_y][porta_x] = rand() % 2 ? '!' : '.';

        for(int j = 0; j < rangedrand(1,2); j++){
            int ent;

            if(rangedrand(0, 4) == 0){
                ent = '$';
            }
            else{
                ent = '*';
            }
            map[rangedrand(y0 + 1, y1 - 1)][rangedrand(x0 + 1, x1 - 1)] = ent;

            if(rangedrand(0, 4) == 0 && n < 15){
                ent = '%';

                coord[n][0] = rangedrand(x0 + 1, x1 - 1);
                coord[n][1] = rangedrand(y0 + 1, y1 - 1);
                map[coord[n][1]][coord[n][0]] = ent;
                n++;
            }
        }
    }

}

int geraCaverna(int argc, const char *argv[]){
    if(argc == 1){
        srand((int) time(NULL));
    }
    else if(argc == 2 && strcmp(argv[1], "--deterministic") == 0){
        srand(0);
    }
    else{
        fprintf(stderr, "Argumento invalido.\n");
        return EXIT_FAILURE;
    }

    for(int y = 0; y < H; y++){
        for(int x = 0; x < W; x++){
            map[y][x] = ' ';
        }
    }

    for(int j = 0; j < 1000; j++){
        caverna(j == 0);
    }

    render();
}

int colisao(char movimento){
    if(movimento == 'w'){
        if(map[player_y - 1][player_x] == '#'){
            return 0;
        }
    }
    else if(movimento == 'd'){
        if(map[player_y][player_x + 1] == '#'){
            return 0;
        }
    }
    else if(movimento == 's'){
        if(map[player_y + 1][player_x] == '#'){
            return 0;
        }
    }
    else if(movimento == 'a'){
        if(map[player_y][player_x - 1] == '#'){
            return 0;
        }
    }
    return 1;
}

void movimentoPlayer(void){
    char movimento;
    movimento = getch();

    if(movimento == 'w' && colisao(movimento) == 1){
        map[player_y][player_x] = '.';
        map[player_y - 1][player_x] = '@';
        player_y--;
    }
    else if(movimento == 'd' && colisao(movimento) == 1){
        map[player_y][player_x] = '.';
        map[player_y][player_x + 1] = '@';
        player_x++;
    }
    else if(movimento == 's' && colisao(movimento) == 1){
        map[player_y][player_x] = '.';
        map[player_y + 1][player_x] = '@';
        player_y++;
    }
    else if(movimento == 'a' && colisao(movimento) == 1){
        map[player_y][player_x] = '.';
        map[player_y][player_x - 1] = '@';
        player_x--;
    }
    /*else if(movimento == 'm'){

    }*/
    else if(movimento == 27){
        menu();
    }
}

int colisaoBot(int movimento, int y, int x){
    if(movimento == 2){
        if(map[y-1][x] == '#' || map[y-1][x] == '?'){
            return 0;
        }
    }
    else if(movimento == 3){
        if(map[y][x+1] == '#' || map[y][x+1] == '?'){
            return 0;
        }
    }
    else if(movimento == 1){
        if(map[y+1][x] == '#' || map[y+1][x] == '?'){
            return 0;
        }
    }
    else if(movimento == 4){
        if(map[y][x-1] == '#' || map[y][x-1] == '?'){
            return 0;
        }
    }
    return 1;
}

void movimentoBot(void){
    int i, j, x, y;
    for(i = 0; i < n; i++){
        for(j = 0; j < 7; j++){
            comandos[i][j] = rangedrand(0, 4);
            //1 - sul(s), 2 - norte (w), 3 - leste(d), 4 - oeste(a) e 0 - parado
        }
    }

    j = 0;
    //j são os comandos e i é o numero do bot
    for(i = 0; i < n; i++){
        y = coord[i][1];
        x = coord[i][0];

        if(comandos[i][j] == 2 && colisaoBot(comandos[i][j], y, x) == 1){
            map[y][x] = '.';
            map[y - 1][x] = '%';
            coord[i][1] = y-1;
        }
        else if(comandos[i][j] == 3 && colisaoBot(comandos[i][j], y, x) == 1){
            map[y][x] = '.';
            map[y][x + 1] = '%';
            coord[i][0] = x+1;
        }
        else if(comandos[i][j] == 1 && colisaoBot(comandos[i][j], y, x) == 1){
            map[y][x] = '.';
            map[y + 1][x] = '%';
            coord[i][1] = y+1;
        }
        else if(comandos[i][j] == 4 && colisaoBot(comandos[i][j], y, x) == 1){
            map[y][x] = '.';
            map[y][x - 1] = '%';
            coord[i][0] = x-1;
        }
        if(i == n-1){
            i = 0;
            movimentoPlayer();
            render();
            j++;
        }
        if(j == 7){
            j = 0;
        }
    }
}

int main(int argc, const char *argv[]){
    geraCaverna(argc, argv);
    movimentoBot();

    return 0;
}
