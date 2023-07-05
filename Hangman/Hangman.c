// Ribesh Joshi 1001829863
#include<stdio.h>
#include<string.h>
#include<stdlib.h>
#include<ctype.h>
#include "GameLib.h"

int main()
{
    char Phrase[MAX_INPUT] = {};
    char DashedPhrase[MAX_INPUT] = {};
    char UpperPhrase[MAX_INPUT] = {};
    int Strikes = 0;
    int i;
    StartGame(Phrase);
    for(i=0;i<MAX_INPUT;i++)
    {
        UpperPhrase[i]=toupper(Phrase[i]);
    }
    DashIt(UpperPhrase,DashedPhrase);
    printf("\nHere's the phrase you need to guess\n");
    do
    {
        if(!(GuessALetter(Phrase,DashedPhrase,UpperPhrase)))
        {
            Strikes++;
            printf("\nStrike %d", Strikes);
        }
    }
    while((strchr(DashedPhrase,'-'))&& Strikes<STRIKES);
    if (Strikes < STRIKES)
    {
    printf("\n\nYou figured it out!!\n\nThe phrase was \n\n%s", Phrase);
    printf("\n\nYOU WIN!!!!\n");
    }
    else
    {   
    printf("\n\n");
    printf("%d STRIKES - YOU'RE OUT!!\n\n", STRIKES);
    printf("Game over\n\n");
    printf("\n\nYou couldn't guess try again next time\n.The phrase was %s\n", Phrase);
    }
    return 0;
}
