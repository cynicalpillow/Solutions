#include <bits/stdc++.h>
using namespace std;
#define pii pair<int,int>
#define mp make_pair
#define pb push_back

int INFINITE = 0x3f3f3f3f;
bool sequence[720];
int D;
int cnt = 0;

int main(){
	//cin.sync_with_stdio(0);
    //cin.tie(0);
    memset(sequence, false, sizeof(sequence));
	freopen("input.txt", "r", stdin);
	scanf("%i", &D);
	for(int i = 0; i < 720; i++){
		int hours = (12+(i / 60));
		if(hours > 12)hours %= 12;
		int secondH = hours % 10;
		int firstH = (hours - secondH)/10;
		int mins = (i % 60);
		int secondM = mins%10;
		int firstM = (mins-secondM)/10;
		if(hours > 9){
			if(((secondH - firstH) == (firstM - secondH)) && ((secondM - firstM) == (firstM - secondH)))sequence[i] = true;
		} else {
			if((secondM - firstM) == (firstM - secondH))sequence[i] = true;
		}
		if(sequence[i])cnt++;
	}
	int result = 0;
	if(D < 720){
		for(int i = 0; i <= D; i++){
			result += sequence[i];
		}
		cout << result;
	} else {
		result += cnt * (D/720);
		int z = D%720;
		for(int i = 0; i <= z; i++){
			result += sequence[i];
		}
		cout << result;
	}
	return 0;
}