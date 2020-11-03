if ("$1" == "")
then
	echo "not implemented"
else
	mkdir zips
	cd "lab$1"
	zip	"../zips/lab$1".zip -r *
fi

