#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <ctype.h>
#include "MovieTheaterLib.h"
#include "QueueLib.h"
#include "BSTLib.h"
#include "ListLib.h"
#include "StackLib.h"

void PrintReceipts(SNODE **StackTop, int num)
{
	if(*StackTop==NULL)
	{
		printf("No receipts \n");
	}
	else
	{
		char store[40]={};
		printf("\nToday's receipts \n");
		while (*StackTop != NULL)
		{
			printf("\nRecipt #%d",(*StackTop)->ReceiptNumber);
			printf("\n\t%s\n",((*StackTop)->MovieTheaterName));
			while(((*StackTop)->TicketList)!=NULL)
			{
				ReturnAndFreeLinkedListNode(&((*StackTop)->TicketList),store);
				printf("\t%s    ",store);
			}
			printf("\n");
			pop(StackTop);
		}
	}
}

BNODE *PickAndDisplayTheater(BNODE *BSTRoot, char MovieTheaterMap[][MAXCOLS], int *MapRow, int *MapCol)
{
	BNODE *MyTheater = NULL;
	char zip[15] = {};
	char *Token=NULL;
	char DM[20]={};
	*MapRow=0;
	*MapCol=0;
	printf("\n\nPick a theater by entering the zipcode\n\n");			
	InOrder(BSTRoot);
	printf("\nEnter Zip:");
	scanf(" %s",zip);
	MyTheater=SearchForBNODE(BSTRoot,zip);
	if(strcmp((MyTheater->ZipCode),zip)!=0)
	{
		printf("Zip Code not available!\n");
	}
	else
	{
		strcpy(DM,MyTheater->Dimensions);
		Token=strtok(DM,"x");
		*MapRow=atoi(Token);
		Token=strtok(NULL,"x");
		*MapCol=atoi(Token);
	}
	if(ReadMovieTheaterFile(MovieTheaterMap,MyTheater->FileName,*MapRow,*MapCol)==0)
	{
		printf("Unable to print the seat map at this time.Check back later\n");
	}
	else
	{
		PrintSeatMap(MovieTheaterMap,*MapRow,*MapCol);
	}
	return MyTheater;
}	

void ReadFileIntoQueue(FILE *QueueFile, QNODE **QH, QNODE **QT)
{
	char fileline[60]={};
	while(fgets(fileline,sizeof(fileline)-1,QueueFile))
	{
		enQueue(fileline,QH,QT);
	}
}

void ReadFileIntoBST(FILE *BSTFile, BNODE **BSTnode)
{
	char fileline[60]={};
	char *Token=NULL;
	char MTN[40]={};
	char ZC[20]={};
	char FN[20]={};
	char DIM[20]={};
	while(fgets(fileline,sizeof(fileline)-1,BSTFile))
	{
		Token=strtok(fileline,"|");
		strcpy(MTN,Token);
		Token=strtok(NULL,"|");
		strcpy(ZC,Token);
		Token=strtok(NULL,"|");
		strcpy(FN,Token);
		Token=strtok(NULL,"|");
		strcpy(DIM,Token);
		AddBSTNode(BSTnode,MTN,ZC,FN,DIM);
	}
}

void get_command_line_parameter(char *argv[], char ParamName[], char ParamValue[])
{
	int i = 0; 
	while (argv[++i] != NULL)
	{
		if (!strncmp(argv[i], ParamName, strlen(ParamName)))
		{
			strcpy(ParamValue, strchr(argv[i], '=') + 1);
		}
	}
}

int PrintMenu()
{
	int choice = 0;
	
	printf("\n\n1.	Sell tickets to next customer\n\n");
	printf("2.	See who's in line\n\n");
	printf("3.	See the seat map for a given theater\n\n");
	printf("4.	Print today's receipts\n\n");
	printf("Choice : ");
	scanf("%d", &choice);
	while (choice < 1 || choice > 4)
	{
		printf("Invalid choice.  Please reenter. ");
		scanf("%d", &choice);
	}
	
	return choice;
}

int main(int argc, char *argv[])
{
	int i, j, k;
	FILE *queueFile = NULL;
	FILE *zipFile = NULL;
	char arg_value[20];
	char queuefilename[20];
	int ReceiptNumber = 0;
	int choice = 0;
	int SeatNumber;
	char Row;
	char Ticket[5];
	int ArrayRow, ArrayCol;
	int MapRow, MapCol;
	char zipfilename[20];
	char MovieTheaterMap[MAXROWS][MAXCOLS] = {};
	LNODE *TicketLinkedListHead = NULL;
	QNODE *QueueHead = NULL;
	SNODE *StackTop = NULL;
	BNODE *BSTRoot = NULL;
	BNODE *MyTheater = NULL;
	QNODE *QueueTail = NULL;
	int NumberOfTickets = 0;
	if (argc < 2)
	{
		ReceiptNumber=1;
		strcpy(zipfilename,"zip.txt");
		strcpy(queuefilename,"queue.txt");
	 //QUEUE=xxxxxx ZIPFILE=xxxxx RECEIPTNUMBER=xxxxx
	
	}
	else{
		get_command_line_parameter(argv, "QUEUE=", queuefilename);
		get_command_line_parameter(argv, "ZIPFILE=", zipfilename);
		get_command_line_parameter(argv, "RECEIPTNUMBER=", arg_value);
		ReceiptNumber = atoi(arg_value);
	}
	queueFile=fopen("queue.txt","r+");
	if(queuefilename==NULL)
	{
		printf("ERROR! Queue file didn't open\n");
		exit(0);
		
	}
	zipFile=fopen("zip.txt","r+");
	if(zipfilename==NULL)
	{
		printf("ERROR! Zip file didn't open\n");
		exit(0);
		
	} 
	ReadFileIntoQueue(queueFile, &QueueHead, &QueueTail);
	ReadFileIntoBST(zipFile, &BSTRoot);
	while (QueueHead != NULL)
	{
		choice = PrintMenu();
	
		switch (choice)
		{
			case 1 :
				printf("\n\nHello %s\n", QueueHead->name);				
				MyTheater = PickAndDisplayTheater(BSTRoot, MovieTheaterMap, &MapRow, &MapCol);
				if (MyTheater != NULL)
				{
					printf("\n\nHow many movie tickets do you want to buy? ");
					scanf("%d", &NumberOfTickets);
					for (i = 0; i < NumberOfTickets; i++)
					{
						do
						{
							printf("\nPick a seat (Row Seat) ");
							scanf(" %c%d", &Row, &SeatNumber);
							Row = toupper(Row);
							ArrayRow = (int)Row - 65;
							ArrayCol = SeatNumber - 1;
							if ((ArrayRow < 0 || ArrayRow >= MapRow) ||
								(ArrayCol < 0 || ArrayCol >= MapCol))
							{
								printf("\nThat is not a valid seat.  Please choose again\n\n");
							}		
						}
						while ((ArrayRow < 0 || ArrayRow >= MapRow) ||
							   (ArrayCol < 0 || ArrayCol >= MapCol));
						
						if (MovieTheaterMap[ArrayRow][ArrayCol] == 'O')
						{	
							MovieTheaterMap[ArrayRow][ArrayCol] = 'X';
							sprintf(Ticket, "%c%d", Row, SeatNumber); 
							InsertNode(&TicketLinkedListHead, Ticket);
						}
						else
						{
							printf("\nSeat %c%d is not available.\n\n", Row, SeatNumber);
							i--;
						}
						PrintSeatMap(MovieTheaterMap, MapRow, MapCol);
					}
					
					WriteSeatMap(MyTheater, MovieTheaterMap, MapRow, MapCol);
					push(&StackTop, TicketLinkedListHead, ReceiptNumber, MyTheater->MovieTheaterName);
					TicketLinkedListHead = NULL;
					ReceiptNumber++;
					printf("\nThank you %s - enjoy your movie!\n", QueueHead->name);
					deQueue(&QueueHead);
				}
				break;
			case 2 : 
				printf("\n\nCustomer Queue\n\n");
				DisplayQueue(QueueHead);
				printf("\n\n");
				break;
			case 3 :
				PickAndDisplayTheater(BSTRoot, MovieTheaterMap, &MapRow, &MapCol);
				break;
			case 4 : 
				PrintReceipts(&StackTop,ReceiptNumber);
				break;
			default :
				printf("Bad menu choice");
		}
	}
	
	printf("Good job - you sold tickets to all of the customers in line.\n");
	PrintReceipts(&StackTop,ReceiptNumber);
	
	
	return 0;
}
