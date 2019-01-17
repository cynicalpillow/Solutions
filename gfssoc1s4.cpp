#include <bits/stdc++.h>
using namespace std;
#define pii pair<int,int>
#define mp make_pair
#define pb push_back

int INFINITE = 0x3f3f3f3f;
long long BIT[255][255][255];
int ss[255][255][255];
int N, Q;

long long sum(int x, int y, int z){
	long long sum = 0;
	int i = y;
	while(i > 0){
		int j = x;
		while(j > 0){
			int k = z;
			while(k > 0){
				sum += BIT[i][j][k];
				k-=(k&-k);
			}
			j-=(j&-j);
		}
		i-=(i&-i);
	}
	return sum;
}

void update(int x, int y, int z, int change){
	int i = y;
	while(i <= N){
		int j = x;
		while(j <= N){
			int k = z;
			while(k <= N){
				BIT[i][j][k] += change;
				k+=(k&-k);
			}
			j+=(j&-j);
		}
		i+=(i&-i);
	}
}

int main(){
	cin.sync_with_stdio(0);
    cin.tie(0);
	freopen("input.txt", "r", stdin);
	cin >> N >> Q;
	long long total = 0;
	for(int i = 0; i < Q; i++){
		string q;
		cin >> q;
		if(q == "C"){
			int x, y, z, l;
			cin >> x >> y >> z >> l;
			int change = l-ss[x][y][z];
			ss[x][y][z] = l;
			update(x, y, z, change);
		} else {
			int x1, y1, z1, x2, y2, z2;
			cin >> x1 >> y1 >> z1 >> x2 >> y2 >> z2;
			total += (sum(x2, y2, z2) - sum(x2, y1-1, z2) - sum(x1-1, y2, z2) - sum(x2, y2, z1-1) + sum(x2, y1-1, z1-1) + sum(x1-1, y1-1, z2) + sum(x1-1, y2, z1-1) - sum(x1-1, y1-1, z1-1));
		}
	}
	cout << total;
	return 0;
}