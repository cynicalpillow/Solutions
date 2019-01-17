#include <bits/stdc++.h>
using namespace std;
#define pii pair<int,int>
#define mp make_pair
#define pb push_back

int INFINITE = 0x3f3f3f3f;
int board[3][3];
string s;

int main(){
	//cin.sync_with_stdio(0);
    //cin.tie(0);
	freopen("input.txt", "r", stdin);
	for(int i = 0; i < 3; i++){
		cin >> s;
		for(int j = 0; j < 3; j++){
			if(s[j] == 'X'){
				board[i][j] = 1;
			}
		}
	}
	bool g = false;
	if((!board[0][0] && !board[1][1] && !board[2][2]) 
		|| (!board[0][2] && !board[1][1] && !board[2][0]))g = true;
	int c = 0;
	for(int j = 0; j < 3; j++){
		if(!board[0][j])c++;
	}
	if(c == 3)g = true;
	c = 0;
	for(int j = 0; j < 3; j++){
		if(!board[1][j])c++;
	}
	if(c == 3)g = true;
	c = 0;
	for(int j = 0; j < 3; j++){
		if(!board[2][j])c++;
	}
	if(c == 3)g = true;

	c = 0;
	for(int j = 0; j < 3; j++){
		if(!board[j][0])c++;
	}
	if(c == 3)g = true;
	c = 0;
	for(int j = 0; j < 3; j++){
		if(!board[j][1])c++;
	}
	if(c == 3)g = true;
	c = 0;
	for(int j = 0; j < 3; j++){
		if(!board[j][2])c++;
	}
	if(c == 3)g = true;

	bool t = false;
	if((board[0][0] && board[1][1] && board[2][2]) 
		|| (board[0][2] && board[1][1] && board[2][0]))t = true;
	c = 0;
	for(int j = 0; j < 3; j++){
		if(board[0][j])c++;
	}
	if(c == 3)t = true;
	c = 0;
	for(int j = 0; j < 3; j++){
		if(board[1][j])c++;
	}
	if(c == 3)t = true;
	c = 0;
	for(int j = 0; j < 3; j++){
		if(board[2][j])c++;
	}
	if(c == 3)t = true;

	c = 0;
	for(int j = 0; j < 3; j++){
		if(board[j][0])c++;
	}
	if(c == 3)t = true;
	c = 0;
	for(int j = 0; j < 3; j++){
		if(board[j][1])c++;
	}
	if(c == 3)t = true;
	c = 0;
	for(int j = 0; j < 3; j++){
		if(board[j][2])c++;
	}
	if(c == 3)t = true;
	if(t && g)cout << "Error, redo";
	else if(t)cout << "Timothy";
	else if(g)cout << "Griffy";
	else cout << "Tie";
	return 0;
}