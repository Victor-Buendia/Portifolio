#include <stdio.h>
#define SPACE " ("

int strTamanho(char str[1001]){
    int i = 0;
    while(str[i] != '\0')
        i++;
    return i;
}

void strCopia(char str1[1001], char str2[1001]){
    int i;
    for(i = 0; str2[i] != '\0'; i++)
        str1[i] = str2[i];
    str1[i] = '\0';
}

void strConcatena(char str1[1001], char str2[1001]){
    int tam, i;

    for(i = 0, tam = strTamanho(str1); str2[i] != '\0'; i++, tam++)
        str1[tam] = str2[i];
    str1[tam] = '\0';
}

void strAdicionaChar(char str1[1001], char c){
    int tam;
    tam = strTamanho(str1);

    str1[tam] = c;
    str1[tam+1] = '\0';
}

void imprimeMensagem(char msg[1001]){
    printf("************************\n");
    printf("*\n");
    printf("* %s\n", msg);
    printf("*\n");
    printf("************************\n");
}

char imprimeCelula(int celula, int etapa){  //renderização (só pra ficar mais fácil de achar)
    if(etapa == 0){
        if(celula == 0){
            return '.';
        }
        else if(celula == -1){
            return '*';
        }
        else if(celula == 1){
            return '1';
        }
        else if(celula == 2){
            return '2';
        }
        else if(celula == 3){
            return '3';
        }
    }
    else{
        if(celula == 0 || celula == -1 || celula == 1 || celula == 2 || celula == 3){
            return '.';
        }
        else if(celula == -2){
            return 'x';
        }
        else if(celula == 10 || celula == 20 || celula == 30){
            return 'N';
        }
        else if(celula == 50){
            return 'A';
        }
    }
}

void imprimeTabuleiro(int mat[10][10], int etapa){
    int i, j, linha = 1;
    printf("     A B C D E F G H I J\n");
    printf("   # # # # # # # # # # # #\n");
    for(i = 0; i < 10; i++){
        if(i == 9){
            printf("10 # %c", imprimeCelula(mat[i][0], etapa));
        }
        else{
            printf("0%d # %c", linha, imprimeCelula(mat[i][0], etapa));
        }
        linha++;
        for(j = 1; j < 10; j++){
            printf(" %c", imprimeCelula(mat[i][j], etapa));
        }
        printf(" #\n");
   }
   printf("   # # # # # # # # # # # #\n");
}

int validaEntradaLinhaColuna(int linha, char coluna){
    if(linha >= 1 && linha <= 10 && coluna >= 65 && coluna <= 74){
        return 1;
    }
    return 0;
}

int validaPosicao(int mat[10][10], int barco, int linha, int coluna, char orientacao){
    int i, j, cod = 1;
    int tam;
    if(mat[linha][coluna] == 0){
        if(orientacao == 'H'){
            for(i = coluna, tam = 0; i < 10 && tam < barco; i++, tam++){
                if(mat[linha][i] != 0){
                    cod = 0;
                }
            }
            if(cod == 1 && tam < barco){
                cod = 0;
            }
        }
        else if(orientacao == 'V'){
            for(i = linha, tam = 0; i < 10 && tam < barco; i++, tam++){
                if(mat[i][coluna] != 0){
                    cod = 0;
                }
            }
            if(cod == 1 && tam < barco){
                cod = 0;
            }
        }
    }
    else
        cod = 0;
    if(cod == 1)
        return 1;
    return 0;
}

void colocaMenosUm(int mat[10][10], int linha, int coluna, char orientacao, int barco){
    int mini, minj, maxi, maxj, aux;

    mini = linha - 1; minj = coluna - 1;
    if(mini < 0){
        mini = 0;
    }
    if(minj < 0){
        minj = 0;
    }

    if(orientacao == 'H'){
        maxj = coluna + barco; maxi = linha + 1;
        if(maxi > 9){
            maxi = 9;
        }
        if(maxj > 9){
            maxj = 9;
        }

        while(mini <= maxi){
            aux = minj;
            while(aux <= maxj){
                if(mat[mini][aux] == 0)
                    mat[mini][aux] = -1;
                aux++;
            }
            mini++;
        }
    }
    else if(orientacao == 'V'){
        maxi = linha + barco; maxj = coluna + 1;
        if(maxi > 9){
            maxi = 9;
        }
        if(maxj > 9){
            maxj = 9;
        }

        while(mini <= maxi){
            aux = minj;
            while(aux <= maxj){
                if(mat[mini][aux] == 0)
                    mat[mini][aux] = -1;
                aux++;
            }
            mini++;
        }
    }
}

void posicionaBarco(int mat[10][10], int barco){
    int linha, validade1, validade2;
    char coluna, orientacao;

    scanf("%d %c", &linha, &coluna);
    if(barco == 1){
        orientacao = 'H';
    }
    else{
        scanf(" %c", &orientacao);
    }
    validade1 = validaEntradaLinhaColuna(linha, coluna);
    linha -= 1; coluna -= 65;
    validade2 = validaPosicao(mat, barco, linha, coluna, orientacao);

    while(validade1 == 0 || validade2 == 0){
        printf("Posicao indisponivel!\n");
        scanf("%d %c", &linha, &coluna);
        if(barco == 1){
            orientacao = 'H';
        }
        else{
            scanf(" %c", &orientacao);
        }
        validade1 = validaEntradaLinhaColuna(linha, coluna);
        linha -= 1; coluna -= 65;
        validade2 = validaPosicao(mat, barco, linha, coluna, orientacao);
    }

    int i;
    if(orientacao == 'H'){
        for(i = coluna; i <= coluna + barco - 1; i++){
            mat[linha][i] = barco;
        }
    }
    else if(orientacao == 'V'){
        for(i = linha; i <= linha + barco - 1; i++){
            mat[i][coluna] = barco;
        }
    }

    colocaMenosUm(mat, linha, coluna, orientacao, barco);

}

void msgDePosicionamento(char msg[], char jogador, char barco, char qtd, char msg3[]){
    char msg1[1001] = "Jogador ";
    char msg2[1001] = " - Posicione o barco de tamanho ";

    strCopia(msg, msg1);    //coloca jogador na msg
    strAdicionaChar(msg, jogador);  //coloca numero do jogador
    strConcatena(msg, msg2);    //coloca posicione o barco na frase a ser exibida
    strAdicionaChar(msg, barco);   //coloca tamanho do barco
    strConcatena(msg, SPACE);   //coloca " (" para facilitar
    strAdicionaChar(msg, qtd);  //colocar a qtd de barco atual
    strConcatena(msg, msg3);    //termina a frase
}

void etapaDePosicionamento(int mat[10][10], int etapa, int barco, char jogador, char *msg){
    char qtd = '1'; char barcoASCII = '1'; 
    while(qtd <= 54){   //6 = 54 (tabela ASCII)
        char msg3[1001] = "/6)";    //se for barco de tamanho 1
        msgDePosicionamento(msg, jogador, barcoASCII, qtd, msg3);
        imprimeMensagem(msg);
        imprimeTabuleiro(mat, etapa);
        posicionaBarco(mat, barco);
        qtd++;
    }

    qtd = '1'; barcoASCII = '2'; barco++;
    while(qtd <= 52){   //6 = 54 (tabela ASCII)
        char msg3[1001] = "/4)";    //se for barco de tamanho 2
        msgDePosicionamento(msg, jogador, barcoASCII, qtd, msg3);
        imprimeMensagem(msg);
        imprimeTabuleiro(mat, etapa);
        posicionaBarco(mat, barco);
        qtd++;
    }

    qtd = '1'; barcoASCII = '3'; barco = 3;
    while(qtd <= 50){   //6 = 54 (tabela ASCII)
        char msg3[1001] = "/2)";    //se for barco de tamanho 3
        msgDePosicionamento(msg, jogador, barcoASCII, qtd, msg3);
        imprimeMensagem(msg);
        imprimeTabuleiro(mat, etapa);
        posicionaBarco(mat, barco);
        qtd++;
    }
}

int podeAtirar(int mat[10][10], int linha, int coluna){
    if(mat[linha][coluna] != -2 && mat[linha][coluna] != 10 && mat[linha][coluna] != 20 && mat[linha][coluna] != 30 && mat[linha][coluna] != 50){
        return 1;
    }
    return 0;
}

void atirar(int mat[10][10], int linha, int coluna){
    if(mat[linha][coluna] == -1 || mat[linha][coluna] == 0){
        mat[linha][coluna] = -2;
    }
    else{
        mat[linha][coluna] = mat[linha][coluna]*10;
    }
}

int calculaPontuacao(int mat[10][10], int linha, int coluna){
    if(mat[linha][coluna] == 10){
        mat[linha][coluna] = 50;
        return 2;
    }
    if(mat[linha][coluna] == 20){
        if(mat[linha][coluna-1] == 20){
            mat[linha][coluna] = 50; 
            mat[linha][coluna-1] = 50;  
            return 4;
            }
        if(mat[linha][coluna+1] == 20){
            mat[linha][coluna] = 50; 
            mat[linha][coluna+1] = 50;  
            return 4;
        }
        if(mat[linha-1][coluna] == 20){
            mat[linha][coluna] = 50; 
            mat[linha-1][coluna] = 50;
            return 4;
        }
        if(mat[linha+1][coluna] == 20){
            mat[linha][coluna] = 50;
            mat[linha+1][coluna] = 50;
            return 4;
        }
    }
    if(mat[linha][coluna] == 30){
        if(mat[linha-1][coluna] == 30 && mat[linha-2][coluna] == 30){
            mat[linha][coluna] = 50;
            mat[linha-1][coluna] = 50;
            mat[linha-2][coluna] = 50;
            return 7;
        }
        if(mat[linha+1][coluna] == 30 && mat[linha+2][coluna] == 30){
            mat[linha][coluna] = 50;
            mat[linha+1][coluna] = 50;
            mat[linha+2][coluna] = 50;
            return 7;     
        }
        if(mat[linha][coluna-1] == 30 && mat[linha][coluna-2] == 30){
            mat[linha][coluna] = 50;
            mat[linha][coluna-1] = 50;
            mat[linha][coluna-2] = 50;
            return 7;            
        }
        if(mat[linha][coluna+1] == 30 && mat[linha][coluna+2] == 30){
            mat[linha][coluna] = 50;
            mat[linha][coluna+1] = 50;
            mat[linha][coluna+2] = 50;
            return 7;            
        }
        if(mat[linha][coluna-1] == 30 && mat[linha][coluna+1] == 30){
            mat[linha][coluna] = 50;
            mat[linha][coluna+1] = 50;
            mat[linha][coluna-1] = 50;
            return 7;             
        }
        if(mat[linha-1][coluna] == 30 && mat[linha+1][coluna] == 30){
            mat[linha][coluna] = 50;
            mat[linha+1][coluna] = 50;
            mat[linha-1][coluna] = 50;
            return 7;                
        }
    }

    return 0;
}

void imprimePontuacao(int pontuacaoJ1, int pontuacaoJ2){
    if(pontuacaoJ1 < 10 && pontuacaoJ2 < 10){
        printf("************************\n");
        printf("*\n");
        printf("* PONTUACAO DO JOGADOR 1: 0%d\n", pontuacaoJ1);
        printf("* PONTUACAO DO JOGADOR 2: 0%d\n", pontuacaoJ2);
        printf("*\n");
        printf("************************\n");
    }
    else if(pontuacaoJ1 >= 10 && pontuacaoJ2 < 10){
        printf("************************\n");
        printf("*\n");
        printf("* PONTUACAO DO JOGADOR 1: %d\n", pontuacaoJ1);
        printf("* PONTUACAO DO JOGADOR 2: 0%d\n", pontuacaoJ2);
        printf("*\n");
        printf("************************\n");
    }
    else if(pontuacaoJ1 < 10 && pontuacaoJ2 >= 10){
        printf("************************\n");
        printf("*\n");
        printf("* PONTUACAO DO JOGADOR 1: 0%d\n", pontuacaoJ1);
        printf("* PONTUACAO DO JOGADOR 2: %d\n", pontuacaoJ2);
        printf("*\n");
        printf("************************\n");
    }
    else{
        printf("************************\n");
        printf("*\n");
        printf("* PONTUACAO DO JOGADOR 1: %d\n", pontuacaoJ1);
        printf("* PONTUACAO DO JOGADOR 2: %d\n", pontuacaoJ2);
        printf("*\n");
        printf("************************\n");        
    } 
}

void msgVez(char jogador, char *msg){
    char vez[17] = "VEZ DO JOGADOR ";

    strCopia(msg, vez);
    strAdicionaChar(msg, jogador);
}

void derrubaNavio(char jogador, char *msg){
    char player[15] = "JOGADOR ";
    char derrubou[25] = " DERRUBOU UM NAVIO!";

    strCopia(msg, player);
    strAdicionaChar(msg, jogador);
    strConcatena(msg, derrubou);
}

int etapaDeJogo(int mat[10][10], int etapa, char jogador, char *msg, int pontuacaoJ1, int pontuacaoJ2){
    int linha, validade1, validade2, aux;
    char coluna;
    
    imprimePontuacao(pontuacaoJ1, pontuacaoJ2);

    msgVez(jogador, msg);
    imprimeMensagem(msg);

    imprimeTabuleiro(mat, etapa);

    scanf("%d %c", &linha, &coluna);
    validade1 = validaEntradaLinhaColuna(linha, coluna);
    linha -= 1; coluna -= 65;
    validade2 = podeAtirar(mat, linha, coluna);
    while(validade1 == 0 || validade2 == 0){
        printf("Posicao indisponivel!\n");
        scanf("%d %c", &linha, &coluna);
        validade1 = validaEntradaLinhaColuna(linha, coluna);
        linha -= 1; coluna -= 65;
        validade2 = podeAtirar(mat, linha, coluna);
    }

    atirar(mat, linha, coluna);
    if(jogador == '1'){
        aux = calculaPontuacao(mat, linha, coluna);
        pontuacaoJ1 += aux;
        if(aux != 0){
            derrubaNavio(jogador, msg);
            imprimeMensagem(msg);
        }
        return pontuacaoJ1;
    }
    else{
        aux = calculaPontuacao(mat, linha, coluna);
        pontuacaoJ2 += aux;
        if(aux != 0){
            derrubaNavio(jogador, msg);
            imprimeMensagem(msg);
        }
        return pontuacaoJ2;
    }
}

int main(void){
    char msg[1001], jogadorASCII;
    int mat1[10][10] = {0}, mat2[10][10] = {0};
    int etapa;
    int barco = 1;
    int pontuacaoJ1 = 0, pontuacaoJ2 = 0;

    //etapa de posicionamento
    etapa = 0;
    jogadorASCII = '1';
    etapaDePosicionamento(mat1, etapa, barco, jogadorASCII, msg);

    jogadorASCII = '2';
    etapaDePosicionamento(mat2, etapa, barco, jogadorASCII, msg);

    //etapa do jogo
    etapa = 1;
    int rodadas = 1;
    while(rodadas <= 20 && pontuacaoJ1 <= 42 && pontuacaoJ2 <= 42){
        jogadorASCII = '1';
        pontuacaoJ1 = etapaDeJogo(mat2, etapa, jogadorASCII, msg, pontuacaoJ1, pontuacaoJ2);

        jogadorASCII = '2';
        pontuacaoJ2 = etapaDeJogo(mat1, etapa, jogadorASCII, msg, pontuacaoJ1, pontuacaoJ2);
        rodadas++;
    }

    //etapa da pontuação final
    char msg2[20] = "FIM DE JOGO";
    imprimeMensagem(msg2);
    imprimePontuacao(pontuacaoJ1, pontuacaoJ2);

    return 0;
}
