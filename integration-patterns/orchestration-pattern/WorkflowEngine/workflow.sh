clean(){
 lein clean
 lein deps
}

runApp(){	
  echo("[info] : starting.")
  lein run -m workflow.controller
}

test(){
  lein test
}

case "$2" in 	
	development)
             case "$1" in
                clean) 
                     clean
                     ;;
                runApp)
                    runApp
                    ;;
                test)
                    test
                    ;;
                *)
                    echo "Usage: $0 {clean|runApp|test}"
                    exit 1
                    ;;
            esac
	    ;;
       *)
          echo "Usage $0 {clean|runApp|test} {dev}"
          exit 2
          ;;
esac 
