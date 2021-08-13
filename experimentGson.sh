#relative path to gson project directory
pathToGson="../ssbseChallenge2019/gson"
#individuals
indCount=2 #21
#generations
genCount=2 #10
#repetitions for test case runs
repCount=10 #500
#donor file name
donor="Donor.java"
#maven home
maven="/usr/share/maven"

#build gin.jar
gradle build
#copy gin.jar to gson
cp build/gin.jar $pathToGson
cp "experiment/${donor}" $pathToGson
#move to gson directory
cd $pathToGson
#run experiment with lines
time java -cp gin.jar gin.util.GPRuntime -d . -p gson -x 2000  -m create_profile_results.csv -h $maven -in $indCount -gn $genCount -r $repCount -et "LINE" -o "results_line.csv"
#run experiment with ReplaceWithTransplantLine only
time java -cp gin.jar gin.util.GPRuntime -d . -p gson -x 2000  -m create_profile_results.csv -h $maven -in $indCount -gn $genCount -r $repCount -et "gin.edit.transplant.ReplaceWithTransplantLine" -o "results_transplant.csv" -ds $donor
#run experiment with Combination of the above
time java -cp gin.jar gin.util.GPRuntime -d . -p gson -x 2000  -m create_profile_results.csv -h $maven -in $indCount -gn $genCount -r $repCount -et "COMBINED_TRANSPLANT_LINE" -o "results_combined.csv" -ds $donor
