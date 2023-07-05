// Ribesh Joshi 1001829863

#include <stdio.h>
#include <string.h>
#include "ListLib.h"

FILE *OpenFile(int argc, char *argv[])
{
	char filename[20];
	FILE *fh;
	int no_filename=0;
    if (argc==2)
    {
        if ((strncmp(argv[1],"FILENAME=",8))==0) // expecting 10% BONUS for adding advance command line parameter to recognize the FILENAME
        {
            char *Delimiters="=";
            char *Token=NULL;
            Token=strtok(argv[1],Delimiters);
            Token=strtok(NULL,Delimiters);
            strcpy(filename,Token);
        }
        else
        {
            strcpy(filename,argv[1]);
        }
    }
	else
	{
		printf("Must be run with input file name\n");
		no_filename=1;
	}
	do
	{
		fh=fopen(filename,"r");
		if(fh==NULL)
		{
			if(no_filename==0)
            {
                printf("Couldn't open the file named %s \n",filename);
            }
			printf("Enter the file name at prompt : ");
			scanf(" %s",filename);
			no_filename=0;
		}
	}
	while (fh==NULL);
	return fh;
}

void ReadFileIntoLinkedList(FILE *DCFile, NODE **LinkedListHead)
{
	char buffer[30]={};
	char *Delimiters="|";
    char *Token=NULL;
	char letter[3]={};
	char command[8]={};
	while(fgets(buffer,sizeof(buffer)-1,DCFile))
	{
		if(buffer[-1]=='\n')
		{
			buffer[-1]='\0';
		}
        Token=strtok(buffer,Delimiters);
		strcpy(letter,Token);
        Token=strtok(NULL,Delimiters);
        strcpy(command,Token);
		AddDrawCommandToList(letter[0],command,LinkedListHead);
	}
}
