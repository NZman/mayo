if [ ! -d "bin" ]; then
	mkdir bin
fi
javac @m_src_list -d bin
