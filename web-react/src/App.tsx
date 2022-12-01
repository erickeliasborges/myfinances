import { ChakraProvider, extendTheme } from "@chakra-ui/react";
import "./App.css";
import { BaseRoutes } from "./routes/BaseRoutes";

const activeLabelStyles = {
  transform: "scale(0.85) translateY(-24px)",
};

//Adiciona configurações de layout para o chakra, pegado da documentação para deixar os labels dos inputs flutuantes
export const theme = extendTheme({
  styles: {
    global: {
      // styles for the `body`
      html: {
        height: '100%'        
      },
    },
  },
  components: {
    Form: {
      variants: {
        floating: {
          container: {
            _focusWithin: {
              label: {
                ...activeLabelStyles,
              },
            },
            "input:not(:placeholder-shown) + label, .chakra-select__wrapper + label, textarea:not(:placeholder-shown) ~ label":
              {
                ...activeLabelStyles,
              },
            label: {
              top: 0,
              left: 0,
              zIndex: 2,
              position: "absolute",
              backgroundColor: "white",
              pointerEvents: "none",
              mx: 3,
              px: 1,
              my: 2,
              transformOrigin: "left top",
            },
          },
        },
      },
    },
  },
});

export function App() {
  return (
    <ChakraProvider theme={theme}>
      <BaseRoutes />
    </ChakraProvider>
  );
}
