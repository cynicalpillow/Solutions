#include <bits/stdc++.h>
using namespace std;
#define pii pair<int,int>
#define mp make_pair
#define pb push_back

int INFINITE = 0x3f3f3f3f;
string st;
string et;

int main(){
	//cin.sync_with_stdio(0);
    //cin.tie(0);
	freopen("input.txt", "r", stdin);
	cin >> st >> et;
	int shours = stoi(st.substr(0, 2));
	int smins = stoi(st.substr(3, 2));
	int ssecs = stoi(st.substr(6, 2));
	int ehours = stoi(et.substr(0, 2));
	int emins = stoi(et.substr(3, 2));
	int esecs = stoi(et.substr(6, 2));
	cout << (ehours*60*60-shours*60*60)+(emins*60-smins*60)+(esecs-ssecs);
	return 0;
}