#include <bits/stdc++.h>
using namespace std;
#define pii pair<int,int>
#define mp make_pair
#define pb push_back

int INFINITE = 0x3f3f3f3f;
int N, X, R, K;
string query;
int BIT[100000005];
map<int, int> rtoid;
map<int, int> idtor;
int ppl = 0;

int sum(int X){
	int sum = 0;
	while(X > 0){
		sum += BIT[X];
		X-=X&-X;
	}
	return sum;
}

void update(int R, int U){
	while(R < 100000005){
		BIT[R] += U;
		R += R&-R;
	}
}

int highestPowerof2(int n){
   int p = (int)log2(n);
   return (int)pow(2, p); 
}

int search(int q){
	int curr = 67108864;
	cout << curr << endl;
	while(true){
		if(BIT[curr] > ppl-q){
			curr = curr>>1;
			cout << curr << endl;
		} else {
			break;
		}
		cout << curr << " " << q << endl;
	}
	int increments = curr>>1;
	while(true){
		if(BIT[curr] > ppl-q){
			curr -= (increments);
			increments = increments>>1;
		} else if(BIT[curr] < ppl-q){
			curr += (increments);
			increments = increments>>1;
			cout << "WTF: " << curr << endl;
		} else {
			break;
		}
		if(BIT[curr] < ppl-q){
			q-=BIT[curr];
		}
		cout << curr << " " << q << endl;
	}
	cout << curr << endl;
	return 0;
}

int main(){
	//cin.sync_with_stdio(0);
    //cin.tie(0);
	freopen("input.txt", "r", stdin);
	memset(BIT, 0, sizeof(BIT));
	scanf("%i", &N);
	for(int i = 0; i < N; i++){
		cin >> query;
		if(query == "N"){
			scanf("%i%i", &X, &R);
			update(R, 1);
			rtoid[R] = X;
			idtor[X] = R;
			ppl++;
		} else if(query == "M"){
			scanf("%i%i", &X, &R);
			update(idtor[X], -1);
			rtoid[idtor[X]] = 0;
			rtoid[R] = X;
			idtor[X] = R;
			update(R, 1);
		} else {
			scanf("%i", &K);
			cout << search(K-1) << "\n";
		}
	}
	return 0;
}