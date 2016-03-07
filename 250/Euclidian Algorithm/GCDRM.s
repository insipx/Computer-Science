	.file	"GCDRM.c"
	.section	.rodata
.LC0:
	.string	"Enter an Integer: "
.LC1:
	.string	"%d"
.LC2:
	.string	"Enter another Integer: "
.LC3:
	.string	"the GCD of %d and %d is %d\n"
	.text
	.globl	main
	.type	main, @function
main:
.LFB0:
	.cfi_startproc
	leal	4(%esp), %ecx
	.cfi_def_cfa 1, 0
	andl	$-16, %esp
	pushl	-4(%ecx)
	pushl	%ebp
	.cfi_escape 0x10,0x5,0x2,0x75,0
	movl	%esp, %ebp
	pushl	%ecx
	.cfi_escape 0xf,0x3,0x75,0x7c,0x6
	subl	$20, %esp
	subl	$12, %esp
	pushl	$.LC0
	call	printf
	addl	$16, %esp
	subl	$8, %esp
	leal	-16(%ebp), %eax
	pushl	%eax
	pushl	$.LC1
	call	__isoc99_scanf
	addl	$16, %esp
	subl	$12, %esp
	pushl	$.LC2
	call	printf
	addl	$16, %esp
	subl	$8, %esp
	leal	-20(%ebp), %eax
	pushl	%eax
	pushl	$.LC1
	call	__isoc99_scanf
	addl	$16, %esp
	subl	$12, %esp
	pushl	$64
	call	DumpS
	addl	$16, %esp
	movl	-20(%ebp), %edx
	movl	-16(%ebp), %eax
	subl	$8, %esp
	pushl	%edx
	pushl	%eax
	call	gcd
	addl	$16, %esp
	movl	%eax, -12(%ebp)
	subl	$12, %esp
	pushl	$64
	call	DumpS
	addl	$16, %esp
	movl	-20(%ebp), %edx
	movl	-16(%ebp), %eax
	pushl	-12(%ebp)
	pushl	%edx
	pushl	%eax
	pushl	$.LC3
	call	printf
	addl	$16, %esp
	movl	$0, %eax
	movl	-4(%ebp), %ecx
	.cfi_def_cfa 1, 0
	leave
	.cfi_restore 5
	leal	-4(%ecx), %esp
	.cfi_def_cfa 4, 4
	ret
	.cfi_endproc
.LFE0:
	.size	main, .-main
	.ident	"GCC: (GNU) 5.3.0"
	.section	.note.GNU-stack,"",@progbits
