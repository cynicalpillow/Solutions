#include <bits/stdc++.h>
using namespace std;
#define fori(i, st, en) for(int i = st; i < (int) en; i++)
#define rfori(i, st, en) for(int i = st; i >= (int) en; i--)
#define f first
#define s second
#define pii pair<int,int>
#define mp make_pair
#define pb push_back

int INFINITE = 0x3f3f3f3f;
int b, n;
char stuffs[5] = {'A', 'B', 'C', 'D', 'E'};

int main(){
	cin.sync_with_stdio(0);
    cin.tie(0);
	freopen("input.txt", "r", stdin);
	while(true){
		cin >> b >> n;
		for(int i = 0; i < n; i++){
			if(b == 1){
				char temp = stuffs[0];
				for(int j = 0; j < 4; j++)stuffs[j] = stuffs[j+1];
				stuffs[4] = temp;
			} else if(b == 2){
				char temp = stuffs[4];
				char temp1[5];
				for(int j = 0; j < 5; j++)temp1[j] = stuffs[j];
				for(int j = 1; j < 5; j++)stuffs[j] = temp1[j-1];
				stuffs[0] = temp;
			} else if(b == 3){
				char temp = stuffs[0];
				stuffs[0] = stuffs[1];
				stuffs[1] = temp;
			} else {
				for(int j = 0; j < 5; j++)
					cout << stuffs[j] << " ";
			}
		}
		if(b == 4 && n == 1)return 0;
	}
	return 0;
}