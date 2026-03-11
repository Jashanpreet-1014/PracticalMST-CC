class Solution {
public:
    bool checkSubarraySum(vector<int>& nums, int k) {
        int n = nums.size();


        int i = 0;
        int sum = 0;
        for(int j = 0; j<n; j++) {
            sum += nums[j];

            if(sum % k == 0 && j - i < 2) return true;
            while(i<j-2 && sum % k != 0) {
                sum -= nums[i];
                i++;
            }
        }

        return false;
    }
};
