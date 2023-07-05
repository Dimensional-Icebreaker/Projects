// Ribesh Joshi 1001829863
#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include"ListLib.h"
#include"FileLib.h"
#include "DrawTool.h"
#include<ctype.h>
#define MAXMAPSIZE 20
int main(int argc, char *argv[])
{
    char mapper[MAXMAPSIZE][MAXMAPSIZE]={};
    int mapsize;
    InitializeMap(mapper);
    char TokenPhrase[40]={};
    char Draw_command[40]={};
    char *Token=NULL;
    char orientation[5]={};
    int row_index=0;
    int coulumn_index=0;
    int number_of_spots=0;
    char symbol[5]={};
    NODE *LinkedListHead ,*TempPtr;
    LinkedListHead=NULL;
    ReadFileIntoLinkedList(OpenFile(argc,argv),&LinkedListHead);
    char choice[15]={};
    do
    {
        printf("Please enter 1-3 choice ");
        scanf(" %s",choice);
    } while (strlen(choice)<1 || strlen(choice)>3);
    int x;
    int shift=0;
    for(x=0;x<strlen(choice);x++,shift+=7)
    {
        choice[x]=toupper(choice[x]);
        TempPtr = FindLetter(LinkedListHead, choice[x],Draw_command);
        //printf("%s draw command is", Draw_command); //testing
        strcpy(TokenPhrase,Draw_command);
        while(Draw_command[0]!='\0')
        {
            Token=strtok(TokenPhrase,"(,)");
            strcpy(orientation,Token);
            Token=strtok(NULL,"(,)");
            row_index=atoi(Token);
            Token=strtok(NULL,"(,)");
            coulumn_index=atoi(Token)+shift;
            Token=strtok(NULL,"(,)");
            number_of_spots=atoi(Token);
            Token=strtok(NULL,"(,)");
            if(Token==NULL || Token == "\n")
            {
                strcpy(symbol,"X\0");
            }
            else
            {
                strcpy(symbol,Token);
            }
            
            if (orientation[0]=='P')
            {
                mapper[row_index][coulumn_index]=symbol[0];
            }
            else
            {
                DrawLine(mapper,row_index,coulumn_index,orientation[0],number_of_spots,symbol[0]);                
            }
            
            TempPtr = FindLetter(TempPtr, choice[x], Draw_command);
            strcpy(TokenPhrase,Draw_command);
        }
    }
    PrintMap(mapper);
    return 0;
}
