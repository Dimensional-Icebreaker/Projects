// Ribesh Joshi 1001829863

#include "ListLib.h"
void AddDrawCommandToList(char letter, char DC[], NODE**LinkedListHead)
{
    NODE *TempPtr, *NewNode;
    NewNode=malloc(sizeof(NODE));
	NewNode->Letter=letter;
	NewNode->next_ptr=NULL;
	NewNode->DrawCommand=malloc(strlen(DC)*sizeof(char)+1);
	strcpy(NewNode->DrawCommand,DC);
	if (*LinkedListHead==NULL)
	{
		*LinkedListHead=NewNode;
	}
	else
	{
		TempPtr=*LinkedListHead;
		while(TempPtr->next_ptr !=NULL)
		{
			TempPtr=TempPtr->next_ptr;
		}
		TempPtr->next_ptr=NewNode;
	}
    
}

NODE *FindLetter(NODE *LinkedListHead, char Letter, char DC[])
{
	NODE *TemppPtr;
	TemppPtr=LinkedListHead;
	while(TemppPtr!=NULL && TemppPtr->Letter!= Letter)
	{
		TemppPtr=TemppPtr->next_ptr;
	}

	if (TemppPtr!=NULL)
	{
		strcpy(DC,TemppPtr->DrawCommand);
		return TemppPtr->next_ptr;
	}
	else
	{
		memset(DC, '\0', sizeof(*DC));  
		return TemppPtr; 
	}
}
